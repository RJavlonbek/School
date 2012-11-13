#! /bin/csh

# Adam Gorman
# COP3353 Unix
# Assignment 4
# 2012/9/26


if ($# != 1) then
    echo "Usage: assignment4.sh <directory_name>"
    exit
endif
if (! -d $1) then
    echo $1": No such directory"
    exit
endif
cd $1
echo "In the directory" `pwd`

set dirCount = 0
set fileCount = 0
set readableCount = 0
set writableCount = 0
set executableCount = 0


foreach filename (*)
    if (-d $filename) then
	set dirCount = `expr $dirCount + 1`
    endif

    if (-f $filename) then
	set fileCount = `expr $fileCount + 1`
    endif

    if (-r $filename) then
	set readableCount = `expr $readableCount + 1`
    endif

    if (-w $filename) then
	set writableCount = `expr $writableCount + 1`
    endif

    if (-x $filename) then    
	set executableCount = `expr $executableCount + 1`
    endif
end

echo "Number of directories :" $dirCount
echo "Number of files :" $fileCount
echo "Number of readable items :" $readableCount
echo "Number of writable items :" $writableCount
echo "Number of executable items :" $executableCount

