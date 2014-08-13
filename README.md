SimpleReminder
==============

A simple reminder/task application written in Java,
supporting command-line and a simple GUI.

SimpleReminder uses [FeatureHouse](http://www.infosun.fim.uni-passau.de/spl/apel/fh/) for feature oriented development.
Visit the project's website to get started, or use [FeatureIDE](http://wwwiti.cs.uni-magdeburg.de/iti_db/research/featureide/) or its Eclipse plugin for an easier configuration and build process.

The default config can be found in `configs/simpleReminder.config`. It uses every currently available feature, but persists data only in common txt files (feature 'binary' is not activated).

Installation
------------

To build a executable jar, run
	`ant build-jar`
on the command-line, from the repository's source folder.

The resulting jar is found in `out/jar`.

Using simpleReminder
-------------------

Currently, no executable or shell script exists for running simpleReminder.

To run simpleReminder, run
	`java -jar <path_to_simpleReminder_jar> [arguments]`
on the command-line with the correct path to the jar and the desired arguments.


If run without any arguments and an active GUI feature (which should be the case if you didn't change the configuration), the GUI is started.

To see a list of command-line arguments, run
	`java -jar <path_to_simpleReminder_jar> help`

All persistent data of simpleReminder can be found in `~/.reminder`.

