dir /s /B "*.java" > src.txt
javac -d bin @src.txt