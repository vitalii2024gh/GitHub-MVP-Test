# This repository is a test task to write sanity tests of 4 MVP GitHub capabilities

First of all it was needed to define what are the 4 MVP capabilities of GitHub. GitHub site was launched in 2008. 
It was 3 years after initial release of Git that became more and more popular. Developers needed to have a centralised 
server for DVCS that allowed them to collaborate easier on common projects. And this was an initial intention of GitHub.
That's why I decided that main functionality of GitHub is an ability to serve pull/push requests as a remote server.
Strictly speaking it's not own GitHub functionality (though I don't know how it works under the hood), it's a functionality
of a standard git server. But this is a core functions of GitHub that were used from the very beginning. The next thing
that is not less important is security. The basic scenario for it - it should not allow to pull/push changes for not
authorised users. And the next thing to check is UI login. It's probably second most important customer-facing feature
after push/pull changes. There is also an option to create repositories that can't be done locally and must be done on a
git server. But it's 5th thing in the sequence of importance and I could add a test for it if it will be requested.

So my tests are:
* Test possibility to pull changes from server
* Test possibility to push changes to server
* Test restrictions for now allowed users
* Test UI login

## Setup
- Clone repository that a test user will have access to. Set path to it in [properties](src/main/resources/common.properties) file.
- Clone repository that a test user will have no access to. Set path to it in [properties](src/main/resources/common.properties) file.
- Set a test user's GitHub credentials in [credentials](src/main/resources/credentials.properties) file.
- The tricky thing is with UI Login test. Normally GitHub requires 2FA for unknown browsers. If we're GitHub developers and we're testing it we'll know how to manage it. It's out of scope of this test task. So a quick workaround for it is to set a breakpoint in [assertions](src/test/java/assertions/UiLoginAssertions.java) file on line 11 and run debug. After you 1st time enter the verification code it will not ask it again and the test will pass without manual actions.

## How to run tests
- Using CLI. Run `./gradlew test` from the root project folder.
- Using IDE.