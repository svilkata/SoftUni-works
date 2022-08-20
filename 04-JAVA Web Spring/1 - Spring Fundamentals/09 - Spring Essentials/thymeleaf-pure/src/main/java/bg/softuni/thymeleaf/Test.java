package bg.softuni.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.StringWriter;

public class Test {
    public static void main(String[] args) {
        TemplateEngine engine = createEngine();
        Context ctx = new Context();
        ctx.setVariable("name", "Pesho");
        StringWriter sw = new StringWriter();

        engine.process("test.html", ctx, sw);

        System.out.println(sw.toString());
    }

    private static TemplateEngine createEngine(){
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(new ClassLoaderTemplateResolver());  //test.html ще бъде в пътя на класа когато се стратира
        return engine;
    }
}
