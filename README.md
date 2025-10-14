[![Maven Central](https://img.shields.io/maven-central/v/network.chaintech/cmpfilepicker.svg)](https://central.sonatype.com/artifact/network.chaintech/cmpfilepicker)
[![Kotlin](https://img.shields.io/badge/kotlin-v2.2.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-v1.8.2-blue)](https://github.com/JetBrains/compose-multiplatform)
[![License](https://img.shields.io/github/license/Chaintech-Network/CMPFilePicker)](http://www.apache.org/licenses/LICENSE-2.0)

![badge-android](http://img.shields.io/badge/platform-android-3DDC84.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-FF375F.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-desktop-4CAF50.svg?style=flat)

# CMPFilePicker - Media & Document Picker for Compose Multiplatform

📁 **CMPFilePicker** is a powerful, easy-to-use library for picking photos, videos, and documents in Compose Multiplatform projects.

<img width="960" alt="Hero-image - CMPFilePicker" src="/assets/banner.jpg"><br><br>

## ✨ Features

- **Photo Picker:** Select images from the camera or gallery.
- **Video Picker:** Pick videos from internal or external sources.
- **Video Player:** Play videos within your app or in an external player.
- **Document Picker:** Select documents (with MIME type filtering) and view PDFs.
- **Document Viewer:** View documents inside your app or with an external viewer.
- **Cross-platform Support:** Works on Android and iOS (desktop/web support planned for the future).
- **Permission Management:** Built-in permission handling and callbacks.
- **Composable API:** Seamlessly integrates with Jetpack Compose.
- **Customizable:** Filter file types, choose single or multiple selection, and handle result callbacks.

---

## 📦 Installation

Add CMPFilePicker to your Compose Multiplatform project:

```kotlin
commonMain.dependencies {
  implementation("network.chaintech:cmpfilepicker:1.1.0")
}
```

---

## 🚀 Usage

### 1️⃣ Create Picker State

```kotlin
val pickerState = rememberMediaPickerState()
```

### 2️⃣ Use MediaPicker Component

```kotlin
MediaPicker(
  state = pickerState,
  onResult = { result ->
    when (result) {
      is MediaResult.Image -> { /* Handle image */ }
      is MediaResult.Video -> { /* Handle video */ }
      is MediaResult.Document -> { /* Handle document */ }
    }
  },
  onPermissionDenied = { deniedPermission ->
    // Show a permission denied dialog
  }
)
```

### 3️⃣ Trigger Picker Actions
For the 'maxCount' parameter you can set:
- **1** - Single selection (picks only one item)
- **0** - Unlimited selection (picks any number of items)
- **Any positive number (e.g., 3, 5, 10)** - Limited selection (picks up to that specific number of items)
```kotlin
// Pick images
picker.pickImage(maxCount = 1) // Pick a single image
picker.pickImage(maxCount = 3) // Pick up to 3 images
picker.pickImage(maxCount = 0) // Pick unlimited images

// Pick videos
picker.pickVideo(maxCount = 1) // Pick a single video
picker.pickVideo(maxCount = 5) // Pick up to 5 videos
picker.pickVideo(maxCount = 0) // Pick unlimited videos

// Pick mixed (images and videos)
picker.pickMixed(maxCount = 1) // Pick a single media item
picker.pickMixed(maxCount = 10) // Pick up to 10 media items
picker.pickMixed(maxCount = 0) // Pick unlimited media items

picker.pickDocument(mimeTypes = listOf("application/pdf"), isSingle = true) // Pick a PDF
picker.pickCamera() // Capture an image from the camera (always single)
```

#### Example: Pick an Image and Handle Result

```kotlin
picker.pickImage()
// The onResult callback will deliver a MediaResult.Image
```

### 4️⃣ Optional: Setup Permission Manager

```kotlin
val permissionsManager = createPermissionsManager(object : PermissionCallback {
  override fun onPermissionStatus(permissionType: PermissionType, status: PermissionStatus) {
    // Handle permission status if needed
  }
})
```

---

## ⚙️ Customization

- **File Type Filtering:**  
  Pass supported MIME types to `pickDocument(mimeTypes = ...)`.
- **Single/Multiple Selection:**  
  Use the `isSingle` parameter in each picker function.
- **Callbacks:**  
  Handle results and permission events via the composable’s `onResult` and `onPermissionDenied`.

---

## 🖼️ Demo

See CMPFilePicker in action:

[//]: # (<img src="/assets/FilePickerDemoImage.jpg" width="300" height="auto" alt="File Picker Demo">)
<img src="/assets/CMPFilePicker-Demo.gif" width="300" height="auto" alt="File Picker Demo Video">

---

## 📄 API Reference

| Function                         | Description                                       |
|:---------------------------------|:--------------------------------------------------|
| `pickImage()`                    | Pick image(s) from the gallery                    |
| `pickVideo()`                    | Pick video(s) from storage                        |
| `pickMixed()`                    | Pick both image(s) and video(s)                   |
| `pickDocument()`                 | Pick document(s), filter by MIME types            |
| `pickCamera()`                   | Capture an image using the camera (single only)   |
| `openVideoInExternalPlayer()`    | Open a video in the external player               |
| `VideoPlayer()`                  | Play a video within your app                      |
| `DocumentScreen()`               | View a document within your app                   |
| `openDocumentInExternalViewer()` | Open a document in an external viewer             |

| Callback               | Description                                    |
|:-----------------------|:-----------------------------------------------|
| `onResult`             | Delivers the selected media/document result    |
| `onPermissionDenied`   | Notifies when permission is denied             |

---

## 🖥️ Platforms Supported

CMPFilePicker currently supports:


| Android 🤖 | iOS 🍎 | Desktop 💻 | Web 🌐 |
|------------|--------|------------|---------------------------|
| ✅          | ✅      | ✅          | ⏳ (Work in Progress)         |

---

## 📖 Detailed Explanation

For an in-depth guide and detailed explanation, check out our comprehensive Medium Blog Post.

[![Medium](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@hanif.s/cmpfilepicker-media-document-picker-for-compose-multiplatform-b7aa2694cd28)

Stay connected and keep up with our latest innovations! 💼 Let's innovate together!

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/showcase/mobile-innovation-network)

<br>

## 📄 License

```
Copyright 2025 Mobile Innovation Network

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
