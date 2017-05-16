Train Route Finder Application
By William Baker

-----------------------------------------------------------

How to run the application

-----------------------------------------------------------

(1) Make sure Java is set in the PATH of your system's Environment Variables.

1. Right-Click "This PC" or "My Computer".
2. Click "Properties".
3. Click "Advanced system settings" on the left.
4. Click "Environment Variables...".
5. Select "Path" under "System variables".
6. Click "Edit".
7. Click "New".
8. Enter the directory for the Java bin folder. For example: "C:\Program Files\Java\jdk1.8.0\bin".
9. Click "Apply" and "OK" until all screens have closed.

(2) Compile the .java files from the .zip.

1. Unzip the files into a folder of your choice.
2. Open the command prompt in that folder. The "app" folder should be in this folder as well.
3. Enter the command: "javac app/trainroutefinder/main/Main.java" and press ENTER.
4. Java should then process the .java files, creating a .class file for each of them.

(3) Run the application.

1. Open the command prompt in the folder above the "app" folder (such that you can see the "app" folder).
2. Enter the command "java app.trainroutefinder.main.Main" and press ENTER.
3. The Java application should appear.

-----------------------------------------------------------

How to open the command prompt in a specific folder

-----------------------------------------------------------

(1) Shift-Right click in Windows Explorer (WINDOWS).

1. Navigate to the folder you would like to open the command prompt in.
2. Shift-Right Click and select "Open command window here".
3. The command prompt should appear.

(2) Use commands to navigate to the folder.

1. Open the command prompt.
2. Enter the command "pushd "DIRECTORY"", where DIRECTORY is the directory you would like to navigate to. (Use "cd" if "pushd" does not work).
3. The command prompt should be in this directory.
