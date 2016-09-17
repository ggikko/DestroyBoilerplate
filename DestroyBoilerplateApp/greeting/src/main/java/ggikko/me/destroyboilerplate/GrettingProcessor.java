package ggikko.me.destroyboilerplate;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by ggikko on 2016. 9. 14..
 */
public class GrettingProcessor extends AbstractProcessor {

  /**
   * Messager, Filter, ElementUtils, TypeUtils, SourceVersion 접근가능, with ProcessingEnvironment
   * @param processingEnv
   */
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
  }

  /** getSupportedAnnotationTypes, getSupportedSourceVersion의 경우에는
   * 예를 들어 자바 7을 사용하면 따로
   * @SupportedSourceVersion
   * @SupportedAnnotationTypes
   * 를 명시해줘야함
   */

  /**
   * 다루어야할 annotation 타입 정의
   * @return
   */
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(SayHello.class.getCanonicalName());
  }

  /**
   * 특정 자바 버전 명시
   * @return
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return super.getSupportedSourceVersion();
  }

  /**
   * 프로세서가 프로세스를 진행하고, RoundEnvironment를 통해 개발자는 여러가지 상황 제약 및 처리할 수 있음
   * @param annotations
   * @param roundEnv
   * @return
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for(Element annotatedElement : roundEnv.getElementsAnnotatedWith(SayHello.class)){
      TypeElement annotatedClass = (TypeElement) annotatedElement;
      String annotatedClassName = annotatedClass.getSimpleName().toString();
      System.out.printf("sdfds");
    }
    return false;
  }

}
