#!/bin/sh
# I had read online that this causes script to be in particular shell
# but in my tests, I am not sure if it works? 

# @name Adam Gorman
# @title COP3353 
# @assignment 3
# @date 9/12/2012

echo "Launching Program..."

echo "1. Copying files..."
cp ~gaitrosd/ufiles/file1 file1
cp ~gaitrosd/ufiles/file2 file2
cp ~gaitrosd/ufiles/grades grades
cp ~gaitrosd/ufiles/t1 t1
cp ~gaitrosd/ufiles/t2 t2

chmod 700 grades


echo "2. Grading..."
echo "=================== Grades Test 1 ===================" > output.txt
grades < t1 >> output.txt

echo "=================== Grades Test 2 ===================" >> output.txt
grades < t2 >> output.txt


echo "3. Changing permissions..."
chmod 644 file1
chmod 644 file2


echo "4. Comparing files..."
echo "=================== diff test 1 ===================" >> output.txt
diff file1 file2 >> output.txt

echo "=================== diff test 2 ===================" >> output.txt
diff -b file1 file2 >> output.txt

echo "=================== diff test 3 ===================" >> output.txt
diff -bi file1 file2 >> output.txt


echo "5. Grepping file1..."
echo "=================== grep test 1 ===================" >> output.txt
grep -in "the" file1 >> output.txt

echo "=================== grep test 2 ===================" >> output.txt
grep -n "^The" file1 >> output.txt

echo "=================== grep test 3 ===================" >> output.txt
grep -inw "who" file1 >> output.txt


echo "6. Generating Process report..."
ps -fu root > process.txt


echo "7. Generating final directory report..."
echo "=================== Directory listing ===================" >> output.txt
ls -l >> output.txt


echo "8. Archiving..."
mkdir -p archive
tar cf a3.tar file1 file2 grades output.txt process.txt
mv a3.tar archive/a3.tar
gzip -qf archive/a3.tar


echo "Exiting script..."
