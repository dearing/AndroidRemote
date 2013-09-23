Classics

Warning
---
I never intended to publish this but I was feeling nostalgic and decided to drop it into my Github museum.  When I wrote this it was in a flash and my first Android project.


About
---
Once upon a time my girlfriend was pregnant with our firstborn and during that time we both had a kick for some old school games.  Legend of Zelda series in particular.  So in for to be able to relax in bed and play without having to constantly git up to my gaming machine to change settings or games or whatever I wrote this android app to act as a remote.


AndroidRemote
---
This is the APK or app that does all the interface work.  It is ugly but functional (well it was 2 years ago when I wrote it) but it simply polls the server for a list of roms on request and launches the emulator with that rom.  All the commands are handled at the server to send keystrokes to the emulators in particular.


AndroidRemoteServer
---
This is a Visual Studio 10 project that crates a network listener for the remote to query for a list of games and where the emulators are that are to be launched.  It sits in the systemtray until clicked to be shut down.  All the settings are handled with built in properties.
