package com.tzj.misc;

interface CarElement {
  void accept(final CarElementVisitor visitor);
}

interface CarElementVisitor {
  void visit(Body body);
  void visit(Engine engine);
  void visit(Car car);
  void visit(Wheel wheel);
}

class CarElementPrintVisitor implements CarElementVisitor {
  @Override
  public void visit(final Car car) {
    System.out.println("Visited car");
  }

  @Override
  public void visit(final Body body) {
    System.out.println("Visited body");
  }

  @Override
  public void visit(final Wheel wheel) {
    System.out.println("Visited " + wheel.getWhich());
  }

  @Override
  public void visit(final Engine engine) {
    System.out.println("Visited engine");
  }
}

class CarElementKickVisitor implements CarElementVisitor {
  @Override
  public void visit(final Car car) {
    System.out.println("Kicked car");
  }

  @Override
  public void visit(final Body body) {
    System.out.println("Kicked body");
  }

  @Override
  public void visit(final Wheel wheel) {
    System.out.println("Kicked " + wheel.getWhich());
  }

  @Override
  public void visit(final Engine engine) {
    System.out.println("Kicked engine");
  }
}

class Car implements CarElement {
  private CarElement[] components;

  public Car() {
    components = new CarElement[] { new Wheel("Left Wheel"), new Wheel("Right Wheel"), new Engine(), new Body() };
  }

  @Override
  public void accept(final CarElementVisitor visitor) {
    for (final CarElement element : components) {
      element.accept(visitor);
    }
    visitor.visit(this);
  }
}

class Body implements CarElement {
  @Override
  public void accept(final CarElementVisitor visitor) {
    visitor.visit(this);
  }
}

class Engine implements CarElement {
  @Override
  public void accept(final CarElementVisitor visitor) {
    visitor.visit(this);
  }
}

class Wheel implements CarElement {
  private String which;

  public Wheel(final String which) {
    this.which = which;
  }

  public String getWhich() {
    return which;
  }

  @Override
  public void accept(final CarElementVisitor visitor) {
    visitor.visit(this);
  }
}

public class VisitorPattern {
  public static void main(String[] args) {
    final Car car = new Car();
    car.accept(new CarElementPrintVisitor());
    car.accept(new CarElementKickVisitor());
  }
}