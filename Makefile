publish:
	lein clean
	lein cljsbuild once min
	scp -r ./resources/* stream:/www-data/deutsch-lernkarten
