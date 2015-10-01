# Winium.Elements for Java

[![GitHub license](https://img.shields.io/badge/license-MPL 2.0-blue.svg?style=flat-square)](../LICENSE)

<p align="center">
<img src="https://raw.githubusercontent.com/2gis/Winium.StoreApps/assets/winium.png" alt="Winium.Elements is a set of extensions for WebDriver Java bindings providing easy-to-use way of interacting with desktop-specific UI elements in Windows Desktop apps tested with Winium.Desktop">
</p>

This is a set of extensions for [WebDriver Java bindings](http://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-remote-driver) providing easy-to-use way of interacting with desktop-specific UI elements in Windows Desktop apps tested with Winium.Desktop.

With the help of Winium.Elements you could simplify interaction with such complex elements like DataGrid, ComboBox, Menu and etc.

## Quick Start
1. Download sources, make it and install to you local maven repository (will be added to Maven Central Repository in near future):
    
    ```bash
    git clone https://github.com/2gis/Winium.Elements.git
    cd Winium.Elements/java
    mvn clean install
    ```
2. Add reference to `winium.elements.desktop` to your pom.xml file:
    
    ```xml
    <dependencies>
        <dependency>
            <groupId>winium.elements.desktop</groupId>
            <artifactId>winium.elements.desktop</artifactId>
            <version>0.1.0-1</version>
        </dependency>
    </dependencies>
    ```
3. Find element and convert it to [ElementType] using WebElementExtensions.to[ElementType] method.
    
    ```java
    WebElement element = driver.findElementById("data_grid");
    DataGrid dataGrid = WebElementExtensions.toDataGrid(element);
    ```
    Use element specific extension methods, for example:
    ```java
    RemoteWebElement cell = dataGrid.find(1, 1);
    Assert.assertEquals("expected cell text", cell.getText());
    ```

## How it works
Winium.Elements extends OpenQA.Selenium bindings adding Winium.Desktop driver commands. 

## Contributing

Contributions are welcome!

1. Check for open issues or open a fresh issue to start a discussion around a feature idea or a bug.
2. Fork the repository to start making your changes to the master branch (or branch off of it).
3. We recommend to write a test which shows that the bug was fixed or that the feature works as expected.
4. Send a pull request and bug the maintainer until it gets merged and published. :smiley:

## Contact

Have some questions? Found a bug? Create [new issue](https://github.com/2gis/Winium.Elements/issues/new) or contact us at zebraxxl@mail.ru

## License

Winium is released under the MPL 2.0 license. See [LICENSE](../LICENSE) for details.
