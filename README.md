# Layla

Layla is a personal [Discord](https://discord.com) bot for myself and frens, mainly to accomplish common tasks we're too
lazy to do manually while also serving as a way for me to maintain my [Kotlin](https://kotlinlang.org) knowledge.

# Self-hosting a development version

### Prerequisites:
* Have a Discord bot application with the message content intent.
* Have [Docker](https://www.docker.com/) and [Git](https://git-scm.com/) installed.

### Procedure:
* `$ git clone https://github.com/SamOphis/layla && cd layla`
* `$ docker build -t layla .`
* `$ docker run -e LAYLA_TOKEN="<Your application token>" layla`

# Contributing
See the [contribution guide](https://github.com/SamOphis/layla/blob/main/CONTRIBUTING.md).