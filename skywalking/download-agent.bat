@echo off
REM SkyWalking Java Agent 下载脚本 (Windows PowerShell)
REM 使用方法: 在项目根目录执行 `skywalking\download-agent.bat`

echo === SkyWalking Java Agent 10.1.0 下载 ===
echo.

set SKYWALKING_VERSION=9.6.0
set AGENT_DIR=skywalking\agent
set DOWNLOAD_URL=https://dlcdn.apache.org/skywalking/java-agent/%SKYWALKING_VERSION%/apache-skywalking-java-agent-%SKYWALKING_VERSION%.tgz
set TEMP_FILE=%TEMP%\skywalking-agent-%SKYWALKING_VERSION%.tgz

if exist "%AGENT_DIR%\skywalking-agent.jar" (
    echo 代理已存在: %AGENT_DIR%\skywalking-agent.jar
    echo 如需重新下载，请先删除 %AGENT_DIR% 目录
    goto :eof
)

echo 正在下载 %DOWNLOAD_URL% ...
powershell -Command "Invoke-WebRequest -Uri '%DOWNLOAD_URL%' -OutFile '%TEMP_FILE%'"
if %ERRORLEVEL% NEQ 0 (
    echo 下载失败，请检查网络连接
    exit /b 1
)

echo 正在解压到 %AGENT_DIR% ...
if not exist "%AGENT_DIR%" mkdir "%AGENT_DIR%"
powershell -Command "tar -xzf '%TEMP_FILE%' -C '%AGENT_DIR%' --strip-components=1"
del "%TEMP_FILE%"

echo.
echo === 下载完成 ===
echo 代理位置: %AGENT_DIR%
echo 核心文件: %AGENT_DIR%\skywalking-agent.jar
echo.
echo 下一步: 参考 skywalking\README.md 配置和使用
