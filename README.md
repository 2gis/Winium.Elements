# Winium.Elements
[![GitHub license](https://img.shields.io/badge/license-MPL 2.0-blue.svg?style=flat-square)](LICENSE)

Winium.Elements provide helpful elements to easily work with specific desktop controls.

## Quick Start
1. Create test project using [Winium Desktop Driver](https://github.com/2gis/Winium.Desktop).
2. Get element and call To<ElementType>.

	```cs
	var element = driver.FindElement(By.Id("data_grid"))
  var dataGrid = element.ToDataGrid();
	```
	After that using extension methods.

## How it works
Winium.Elements extend OpenQA.Selenium bindings for using additional methods Winium.Desktop driver such as "getDataGridCell". 
