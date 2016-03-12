# FamFamFlags Add-on for Vaadin 7

FamFamFlags is an icon resource collection add-on for Vaadin 7.

## Online demo

Try the add-on demo at https://maxschuster.jelastic.servint.net/FamFamFlags/

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/FamFamFlags

## Building and running demo

git clone https://github.com/maxschuster/Vaadin-FamFamFlags.git
mvn clean install
cd vaadin-famfamflags-demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/
 
## Release notes

### Version 1.0.0
- Initial release

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

FamFamFlags is written by Max Schuster

The [Flags](http://www.famfamfam.com/lab/icons/flags/) icon set was created by 
Mark James (http://www.famfamfam.com/) and is public domain.

See: [licenses/famfamfam-flags.txt](licenses/famfamfam-flags.txt).

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java
Button button = new Button();
button.setCaption("Finland");
button.setIcon(FamFamFlags.FINLAND);
```