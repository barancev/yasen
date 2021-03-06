package ru.stqa.yasen.testenv;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.yasen.Browser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Fixture implements ExtensionContext.Store.CloseableResource {

  private final Map<Thread, TestEnvironment> envs = new ConcurrentHashMap<>();
  private final Map<Thread, Browser> drivers = new ConcurrentHashMap<>();

  TestEnvironment getTestEnv() {
    return envs.computeIfAbsent(Thread.currentThread(), (t) -> new TestEnvironment());
  }

  Browser getBrowser() {
    System.setProperty("webdriver.firefox.logfile", "/dev/null");
    return drivers.computeIfAbsent(Thread.currentThread(), (t) -> new Browser(new FirefoxDriver()));
  }

  @Override
  public void close() {
    drivers.forEach((k, browser) -> browser.close());
    envs.forEach((k, env) -> env.cleanup());
  }
}
