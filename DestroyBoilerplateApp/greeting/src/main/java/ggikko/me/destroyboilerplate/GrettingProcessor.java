package ggikko.me.destroyboilerplate;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by ggikko on 2016. 9. 14..
 */
public class GrettingProcessor extends AbstractProcessor {

  private Messager messager;
  private Elements elementUtils;
  private Filer filer;
  private static final String SUFFIX = "Ggikko";
  private String qualifiedClassName;

  /**
   * Messager, Filter, ElementUtils, TypeUtils, SourceVersion 접근가능, with ProcessingEnvironment
   */
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    messager = processingEnv.getMessager();
    elementUtils = processingEnv.getElementUtils();
    filer = processingEnv.getFiler();
    super.init(processingEnv);
  }

  /** getSupportedAnnotationTypes, getSupportedSourceVersion의 경우에는
   * 예를 들어 자바 7을 사용하면 따로
   * @SupportedSourceVersion
   * @SupportedAnnotationTypes
   * 를 명시해서 아래의 메소드를 없앨 수 있음.
   */

  /**
   * 다루어야할 annotation 타입 정의
   */
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(SayHello.class.getCanonicalName());
  }

  /**
   * 특정 자바 버전 명시
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }


  /**
   * 프로세서가 프로세스를 진행하고, RoundEnvironment를 통해 개발자는 여러가지 상황 제약 및 처리할 수 있음
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(SayHello.class)) {

      if (annotatedElement.getKind() != ElementKind.CLASS) {
        error(annotatedElement, "Only classes can be annotated with @%s", SayHello.class.getSimpleName());
        return true;
      }

      qualifiedClassName = SUFFIX + annotatedElement.getSimpleName().toString();

      try {
        generateCode(elementUtils, filer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


    return false;
  }

  /**
   * Messager를 이용한 명시적 에러 처리
   */
  private void error(Element e, String msg, Object... args) {
    messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
  }

  /**
   * 자바 코드 생성
   */
  public void generateCode(Elements elementUtils, Filer filer) throws IOException {
//    TypeElement superClassName = elementUtils.getTypeElement(qualifiedClassName);
//    String factoryClassName = superClassName.getSimpleName() + SUFFIX;
//    PackageElement pkg = elementUtils.getPackageOf(superClassName);

    StringBuilder builder = new StringBuilder()
        .append("package ggikko.me.destroyboilerplateapp;\n\n")
        .append("public class GgikkoClass {\n\n") // open class
        .append("\tpublic String getMessage() {\n") // open method
        .append("\t\treturn \"");

    // this is appending to the return statement
    builder.append("Ggikko").append(" says hello!\\n");

    builder.append("\";\n")
        .append("\t}\n")
        .append("}\n");

    try { // write the file
      JavaFileObject source = processingEnv.getFiler().createSourceFile("ggikko.me.destroyboilerplateapp.GgikkoClass");

      Writer writer = source.openWriter();
      writer.write(builder.toString());
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
