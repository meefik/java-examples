package geometry;

// в интерфейсах возможно использовать наследование
// interface Figure extends AbsFigure {}
interface Figure {

    // автоматически объявлен как static final
    String INTERFACE_NAME = "Figure";

    // определения методов недопустимы

    double getArea();       // автоматически объявлен как public
    double getPerimeter();  // автоматически объявлен как public
    
}
