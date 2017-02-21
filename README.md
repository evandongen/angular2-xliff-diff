# angular2-xliff-diff
Diffs two xliff files from ng-xi18n and outputs the new and deleted items

Since Angular2 introduced the i18n functions, I've been struggling with getting new and changed strings in my 
application in different languages. I've created this simple java project to compare two files you can place in 
**src/main/resources**.

It uses Jackson to import the files to objects and those two collections are compared and serialized to XML again. You 
can use the output to add the missing strings to your translated file to make sure Angular builds again without errors.

As you can see in the unit test the main method returns an object with the deleted and added messages. You can use this 
object to write to a file if you instead of using the standard out.

Usage:
 * Build the project with maven, eg. `mvn clean package`
 * Run the jar in your target folder: `java -jar angular2-xliff-diff-0.0.1-SNAPSHOT.jar`
 * Or run the jar in your target folder with custom file paths: `java -DlatestFile=/path/to/your/latest-file.xlf -DpreviousFile=/path/to/your/previous-file.xlf -jar angular2-xliff-diff-0.0.1-SNAPSHOT.jar`
  
Or you can just clone the project and run the `I18nDiffApplication` in your favorite IDE and modify the default files 
in src/main/resources.

### Update 21 February 2017

When starting with some 'real' translations I noticed a few problems. One of them was that the id changes based on 
changed content, even for a space or tab. Very annoying. That caused changed items to be displayed in the added and 
removed output with different keys. I've changed the code to only support ONE note in a trans unit which was already 
the case in my code.