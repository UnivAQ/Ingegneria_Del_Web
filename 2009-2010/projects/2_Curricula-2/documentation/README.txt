[ Dependencies ]

To get the project dependencies, you need to have installed:
 - Ant 1.8
 - Maven 2
and to be connected to Internet.

Be sure to include the "ant" and the "mvn" binary directories
in the PATH env variable.

Open a terminal and change the working directory to the directory with this README.

Run:
ant -f ant.xml

Now you have all the dependencies needed by this project installed on your system.

You may have to include the jars in your ide library.
They are located under the "sandbox/deps/WEB-INF/lib/" directory.

Congratulations, you have finished.



[ Mercurial ]

If you have to handle the Mercurial SCM, you may find useful these commands:

# It gets you statistics about the work done on the local repository.
hg stat

# It saves your work.
# This is the first step to share your work with your team.
hg commit -A -m "Insert here a one line short comment about the work done."

# It sends your work to the central repository.
hg push

# It gets the latest changes from the central repository
# and puts them on your local repository.
hg pull
hg update

# For the complete command list type:
hg help
# or
hg help %{command}
# for example, "hg help commit"



[ References ]

The Main Source Repository:
http://bitbucket.org/daniele_orlando/ingweb/

The Project Dashboard
https://wave.google.com/wave/waveref/googlewave.com/w+sQoj2mC-C
