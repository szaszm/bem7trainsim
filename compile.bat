@echo off
pushd C:\Program Files\Java
for /f "delims=" %%i in ('dir /b/s javac.exe') do set JAVAC=%%i
popd
rmdir /S /Q out
mkdir out
call "%%javac%%" -sourcepath src -d out -encoding utf-8 src/bem7trainsim/Main.java
