.PHONY: help
.PHONY: docker-build docker-start docker-stop docker-status docker-up

.DEFAULT_GOAL := help

help:
	@echo "\n${ORANGE}usage: make ${GREEN}COMMAND${NC}"
	@echo "\nDocker commands:"
	@echo "  ${GREEN}docker-up${NC}            : Create and start containers"
	@echo "  ${GREEN}docker-start${NC}         : Start containers"
	@echo "  ${GREEN}docker-down${NC}          : Stop and clear all services"
	@echo "  ${GREEN}docker-logs${NC}          : Follow log output"
	@echo "  ${GREEN}docker-status${NC}        : List of containers"
	@echo "  ${GREEN}docker-build${NC}         : Build all containers"
	@echo "\n"

docker-up:
	@echo "${GREEN}Docker up all containers:${NC}"
	@docker-compose -f docker-compose.yaml up -d
docker-start:
	@echo "${GREEN}Starting all containers:${NC}"
	@docker-compose -f docker-compose.yaml start
docker-down:
	@echo "${GREEN}Remove all containers:${NC}"
	@docker-compose down -v --remove-orphans
docker-stop:
	@echo "${GREEN}Stopping all containers:${NC}"
	@docker-compose stop
docker-logs:
	@docker-compose logs -f
docker-status:
	@echo "${GREEN}Listing all containers:${NC}"
	@docker-compose ps
docker-build:
	@echo "${BLUE}Build and create:${NC}"
	@docker-compose -f docker-compose.yaml build --pull --no-cache

# Colors
RED=\033[0;31m
LIGHT_RED=\033[1;31m
GREEN=\033[0;32m
LIGHT_GREEN=\033[1;32m
ORANGE=\033[0;33m
YELLOW=\033[1;33m
BLUE=\033[0;34m
LIGHT_BLUE=\033[1;34m
PURPLE=\033[0;35m
LIGHT_PURPLE=\033[1;35m
CYAN=\033[0;36m
LIGHT_CYAN=\033[1;36m
NC=\033[0m
