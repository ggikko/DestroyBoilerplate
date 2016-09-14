package ggikko.me.destroyboilerplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by ggikko on 2016. 9. 14..
 */
public class GrettingProcessor extends AbstractProcessor {

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(SayHello.class.getCanonicalName());
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for(Element annotatedElement : roundEnv.getElementsAnnotatedWith(SayHello.class)){
      TypeElement annotatedClass = (TypeElement) annotatedElement;
      String annotatedClassName = annotatedClass.getSimpleName().toString();
    }
    return false;
  }

}
