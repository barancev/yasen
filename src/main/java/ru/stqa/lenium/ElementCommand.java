package ru.stqa.lenium;

import org.openqa.selenium.WebElement;
import ru.stqa.trier.TimeBasedTrier;

import java.util.function.Function;
import java.util.function.Supplier;

class ElementCommand<R> implements Supplier<R> {

  private Supplier<WebElement> elementSupplier;
  private Function<WebElement, R> command;

  ElementCommand(Supplier<WebElement> elementSupplier, Function<WebElement, R> command) {
    this.elementSupplier = elementSupplier;
    this.command = command;
  }

  @Override
  public R get() {
    try {
      return new TimeBasedTrier<R>(60000).tryTo(() -> command.apply(elementSupplier.get()));
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}

