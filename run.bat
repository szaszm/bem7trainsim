@echo off
pushd C:\Program Files\Java
for /f "delims=" %%i in ('dir /b/s java.exe') do set JAVA=%%i
popd
call "%%java%%" -classpath out bem7trainsim.Main
