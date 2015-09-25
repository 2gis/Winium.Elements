# Winium.Elements for .Net

[![Build Status](https://img.shields.io/jenkins/s/http/opensource-ci.2gis.ru/Winium.Elements.svg?style=flat-square)](http://opensource-ci.2gis.ru/job/Winium.Elements/)
[![Inner Server NuGet downloads](https://img.shields.io/nuget/dt/Winium.Elements.Desktop.svg?style=flat-square)](https://www.nuget.org/packages/Winium.Elements/)
[![Inner Server NuGet version](https://img.shields.io/nuget/v/Winium.Elements.Desktop.svg?style=flat-square)](https://www.nuget.org/packages/Winium.Elements/)
[![GitHub license](https://img.shields.io/badge/license-MPL 2.0-blue.svg?style=flat-square)](LICENSE)

## Quick Start
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
