#!/bin/bash
# SkyWalking Java Agent 下载脚本
# 使用方法: bash skywalking/download-agent.sh

set -e

SKYWALKING_VERSION="9.6.0"
AGENT_DIR="skywalking/agent"
DOWNLOAD_URL="https://dlcdn.apache.org/skywalking/java-agent/${SKYWALKING_VERSION}/apache-skywalking-java-agent-${SKYWALKING_VERSION}.tgz"

echo "=== SkyWalking Java Agent ${SKYWALKING_VERSION} 下载 ==="
echo ""

# 检查是否已存在
if [ -f "${AGENT_DIR}/skywalking-agent.jar" ]; then
    echo "代理已存在: ${AGENT_DIR}/skywalking-agent.jar"
    echo "如需重新下载，请先删除 ${AGENT_DIR} 目录"
    exit 0
fi

TEMP_FILE="/tmp/skywalking-agent-${SKYWALKING_VERSION}.tgz"

echo "正在下载 ${DOWNLOAD_URL} ..."
if command -v wget &> /dev/null; then
    wget -q --show-progress -O "${TEMP_FILE}" "${DOWNLOAD_URL}"
elif command -v curl &> /dev/null; then
    curl -L -o "${TEMP_FILE}" "${DOWNLOAD_URL}"
else
    echo "错误: 需要 wget 或 curl 来下载文件"
    exit 1
fi

echo "正在解压到 ${AGENT_DIR} ..."
mkdir -p "${AGENT_DIR}"
tar -xzf "${TEMP_FILE}" -C "${AGENT_DIR}" --strip-components=1
rm -f "${TEMP_FILE}"

echo ""
echo "=== 下载完成 ==="
echo "代理位置: ${AGENT_DIR}"
echo "核心文件: ${AGENT_DIR}/skywalking-agent.jar"
echo ""
echo "下一步: 参考 skywalking/README.md 配置和使用"
