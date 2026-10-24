name: Build Android APK

on:
  push:
    branches: [ "main" ]
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout Source Code
      uses: actions/checkout@v4

    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        distribution: 'temurin'
        java-version: '17'

    - name: Setup Android SDK
      uses: android-actions/setup-android@v3

    - name: Build with Gradle
      run: ./gradlew assembleDebug --stacktrace

    - name: Upload APK to Phone
      uses: actions/upload-artifact@v4
      with:
        name: acheteur-pro-release
        path: app/build/outputs/apk/debug/*.apk
