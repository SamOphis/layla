# Layla

Layla is a personal [Discord](https://discord.com) bot for myself and frens, mainly to accomplish common tasks we're too
lazy to do manually while also serving as a way for me to maintain my [Kotlin](https://kotlinlang.org) knowledge.

# Self-hosting a development version
Below are two ways to self-host Layla: with [Docker](https://docker.com) and without.
The bot is ridiculously simple right now so would advise you to go with the manual approach if you already have the
required dependencies installed & running.

### With Docker (requires Git & Docker):
* `$ git clone https://github.com/SamOphis/layla && cd layla`
* `$ docker build -t layla .`
* `$ docker run -e LAYLA_TOKEN="<Your application token>" layla`

### Manually (requires Git & Java 17):
* `$ git clone https://github.com/SamOphis/layla && cd layla`
* `$ LAYLA_TOKEN="<Your application token>" gradlew run`

# Contributing
See the [contribution guide](https://github.com/SamOphis/layla/blob/main/CONTRIBUTING.md).