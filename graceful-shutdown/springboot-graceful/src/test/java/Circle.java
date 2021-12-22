public class Circle {
    Point center = new Point();
    int radius;
    public Circle(Point c,int r){
        center = c;
        radius = r;
    }
    public Circle(int x,int y, int r){
        center.x = x;
        center.y = y;
        radius = r;
    }
    public void setX(int x){
        center.x = x;
    }

    public static void main(String[] args) {
        Point a = new Point();
        Circle c = new Circle(a,1);
        c.setX(10);
        System.out.println(a.x);
        Circle q = new Circle(a.x,a.y,1);
        c.setX(20);
        System.out.println(a.x);
    }
}

class Point{
    int x = 0;
    int y =0;
}