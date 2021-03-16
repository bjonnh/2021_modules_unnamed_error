# This is the non-working branch

This was for me to try to understand how that whole Java module system worked and why I had so many issues.

I had trouble using Gradle 7.0 with libraries that do not have a module-info.java so cannot be used directly in a modular build. 

Following the discussion with the gradle devs:
<https://github.com/gradle/gradle/issues/16541>

And following their advice, you will find the working version in the following branch that uses [jjohannes/extra-java-module-info](https://github.com/jjohannes/extra-java-module-info):

[branch using-extra-java-module-info](https://github.com/bjonnh/2021_modules_unnamed_error/tree/using-extra-java-module-info)
