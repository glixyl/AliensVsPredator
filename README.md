AliensVsPredator
=============

A Minecraft Mod, based entirely off of the AliensVsPredator franchise. It's also based off of other related franchises. That means it includes 
everything from xenomorphs to predaliens, plasma casters to pulse rifles, equipment to study the species with, dimensions, 
and of course plenty of blocks to start Building Better Worlds™


## Links ##
* [Source]
* [Wiki]
* [Issues]
* [Website]

## Prerequisites ##
* [Java]

## Contributing ##
I have a general set of [rules] I follow for my projects.
Do read through it if you do plan on contributing.

## Clone ##
The following steps will ensure your project is cloned properly

1. `git clone git@github.com:Ri5ux/AliensVsPredator.git`
2. `cd AliensVsPredator`

## Development Environment ##
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for
Windows systems in place of any 'gradle' command.

If you are a contributor, it is important that your development environment is setup properly. After cloning, as shown
above, follow the given steps for your ide:

#### [IntelliJ]

1. `gradle setupDecompWorkspace --refresh-dependencies`
2. `gradle idea`

#### [Eclipse]

1. `gradle setupDecompWorkspace --refresh-dependencies`
2. `gradle eclipse`

## Updating your Clone ##
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for
Windows systems in place of any 'gradle' command.

The following steps will update your clone with the official repo.

* `git pull`
* `gradle --refresh-dependencies`

## Building
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for
Windows systems in place of any 'gradle' command.

We use [Gradle] for AliensVsPredator.

In order to build AliensVsPredator you simply need to run the `gradle` command.
You can find the compiled JAR file in `./build/libs` labeled similarly to 'AliensVsPredator-x.x.jar'.

[Source]: https://github.com/Ri5ux/AliensVsPredator
[Wiki]: https://github.com/Ri5ux/AliensVsPredator/wiki
[Issues]: https://github.com/Ri5ux/AliensVsPredator/issues
[Website]: http://aliensvspredator.org/
[Java]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[rules]: Contributors.md
[IntelliJ]: https://www.jetbrains.com/idea/
[Eclipse]: https://www.eclipse.org/
[Gradle]: https://www.gradle.org/
