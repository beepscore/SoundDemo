# Purpose
Write Android app to play sound.

# References
Udemy complete android developer course
Section 4 lecture 50
https://www.udemy.com/the-complete-android-developer-course/learn/v4

# Results
Currently this app does not handle activity restart very well.
For example if user rotates device, the activity restarts.
Audio playback continues but time seekbar returns to 0.

App uses a timer with a runnable to update timer seek bar.

