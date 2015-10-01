# Winium.Elements

[![Build Status](https://img.shields.io/jenkins/s/http/opensource-ci.2gis.ru/Winium.Elements.svg?style=flat-square)](http://opensource-ci.2gis.ru/job/Winium.Elements/)
[![GitHub license](https://img.shields.io/badge/license-MPL 2.0-blue.svg?style=flat-square)](LICENSE)

<p align="center">
<img src="https://raw.githubusercontent.com/2gis/Winium.StoreApps/assets/winium.png" alt="Winium.Elements is a set of extensions for WebDriver C# bindings providing easy-to-use way of interacting with desktop-specific UI elements in Windows Desktop apps tested with Winium.Desktop">
</p>

This is a set of extensions for [WebDriver Clients Bindings](http://www.seleniumhq.org/download/) providing easy-to-use way of interacting with desktop-specific UI elements in Windows Desktop apps tested with Winium.Desktop.

With the help of Winium.Elements you could simplify interaction with such complex elements like DataGrid, ComboBox, Menu and etc.

## Quick Start (.NET example)
1. Add reference to `Winium.Elements.Desktop` in UI test project ([install NuGet package](https://www.nuget.org/packages/Winium.Elements.Desktop/)).
2. Find element and convert it to [ElementType] using To[ElementType] method.
	
	```cs
	var element = driver.FindElementById("data_grid");
	var dataGrid = element.ToDataGrid();
	```
	Use element specific extension methods, for example:
	```cs
	var cell = dataGrid.Find(1, 1);
	Assert.AreEqual("expected cell text", cell.Text);
	```
	
[Read more...](/dotnet/README.md)

## How it works
Winium.Elements extends OpenQA.Selenium bindings adding Winium.Desktop driver commands. 

## Contributing

Contributions are welcome!

1. Check for open issues or open a fresh issue to start a discussion around a feature idea or a bug.
2. Fork the repository to start making your changes to the master branch (or branch off of it).
3. We recommend to write a test which shows that the bug was fixed or that the feature works as expected.
4. Send a pull request and bug the maintainer until it gets merged and published. :smiley:

## Contact

Have some questions? Found a bug? Create [new issue](https://github.com/2gis/Winium.Elements/issues/new)

## License

Winium is released under the MPL 2.0 license. See [LICENSE](LICENSE) for details.
