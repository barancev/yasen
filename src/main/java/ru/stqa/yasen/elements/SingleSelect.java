package ru.stqa.yasen.elements;

import ru.stqa.yasen.Element;
import ru.stqa.yasen.ElementInList;
import ru.stqa.yasen.ElementWrapper;

import java.util.Optional;

public class SingleSelect extends ElementWrapper {

  public SingleSelect(Element element) {
    super(element);
  }

  public SingleSelect selectByValue(String value) {
    element.$(String.format("option[value='%s']", value)).click();
    return this;
  }

  public Optional<ElementInList> selectedOption() {
    return element.$$("option").stream().filter(Element::isSelected).findFirst();
  }
}
