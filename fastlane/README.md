fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android dev

```sh
[bundle exec] fastlane android dev
```

Deploy a new app version of dev environment

### android live

```sh
[bundle exec] fastlane android live
```

Deploy a new app version of live environment

### android execute_deployment

```sh
[bundle exec] fastlane android execute_deployment
```

Executes build deployment alongside apk/aab generation and CI/CD operations

### android build_apk

```sh
[bundle exec] fastlane android build_apk
```

Build daily scoop app apk

### android build_bundle

```sh
[bundle exec] fastlane android build_bundle
```

Build daily scoop app bundle

### android send_slack_message

```sh
[bundle exec] fastlane android send_slack_message
```

Sends a slack notification message when the performed deployment succeeded

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
