#!/bin/bash
# 下载 Sentinel Dashboard 1.8.8 JAR
# 用法：bash download.sh

VERSION=1.8.8
URL="https://github.com/alibaba/Sentinel/releases/download/${VERSION}/sentinel-dashboard-${VERSION}.jar"
TARGET="sentinel-dashboard.jar"

echo "正在下载 Sentinel Dashboard ${VERSION} ..."
curl -L -o "${TARGET}" "${URL}" && echo "✅ 下载完成: $(du -h ${TARGET} | cut -f1)" || echo "❌ 下载失败，请检查网络"
