# angular2-xliff-diff
Diffs two xliff files from ng-xi18n and outputs the added, changed and removed items.

Since Angular2 introduced the i18n functions, I've been struggling with getting new and changed strings in my 
application in different languages. I've created this simple java project to compare two files you can place in 
**src/main/resources**.

It uses Jackson to import the files to objects and those two collections are compared and serialized to XML again. You 
can use the output to add the missing strings to your translated file to make sure Angular builds again without errors.

As you can see in the unit test the main method returns an object with the deleted and added messages. You can use this 
object to write to a file if you want instead of using the standard out.

Usage:
 * Build the project with maven, eg. `mvn clean package`
 * Run the jar in your target folder: `java -jar angular2-xliff-diff-0.0.1-SNAPSHOT.jar`
   This will default to `messages.latest.xlf` and `messages.previous.xlf` in `src/main/resources`
 * Or run the jar in your target folder with custom file paths: `java -DlatestFile=/path/to/your/latest-file.xlf -DpreviousFile=/path/to/your/previous-file.xlf -jar angular2-xliff-diff-0.0.1-SNAPSHOT.jar`
  
Or you can just clone the project and run the `I18nDiffApplication` in your favorite IDE and modify the default files 
in src/main/resources.

### Update May 31 2017
Added generated output for the changed id's. Since the files used as input are typically the 'terms', other files in use 
for other languages need to be processed as well. With the generated replace commands (sed), you can easily replace the 
id's in multiple documents. 

### Update 14 March 2017
Added counts in the output to easily be able to see the number of changed/removed/added items.

### Update 21 February 2017
When starting with some 'real' translations I noticed a few problems. One of them was that the id changes based on 
changed content, even for a space or tab. Very annoying. That caused changed items to be displayed in the added and 
removed output with different keys. I've changed the code to only support ONE note in a trans unit which was already 
the case in my code.
