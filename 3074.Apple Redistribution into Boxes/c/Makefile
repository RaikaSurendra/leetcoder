# Makefile for Apple Redistribution Problem - C Implementation

# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -std=c11 -O3 -Iinclude
DEBUG_FLAGS = -g -O0
BENCH_FLAGS = -O3 -march=native

# Directories
SRC_DIR = src
INCLUDE_DIR = include
TEST_DIR = test
BUILD_DIR = build
BIN_DIR = bin

# Source files
GREEDY_SRC = $(SRC_DIR)/solution_greedy.c
OPTIMIZED_SRC = $(SRC_DIR)/solution_optimized.c
TEST_SRC = $(TEST_DIR)/test.c

# Object files
GREEDY_OBJ = $(BUILD_DIR)/solution_greedy.o
OPTIMIZED_OBJ = $(BUILD_DIR)/solution_optimized.o
TEST_OBJ = $(BUILD_DIR)/test.o

# Executables
TEST_EXEC = $(BIN_DIR)/test_solution

.PHONY: all clean test debug dirs help

# Default target
all: dirs $(TEST_EXEC)

# Create necessary directories
dirs:
	@mkdir -p $(BUILD_DIR) $(BIN_DIR)

# Build test executable
$(TEST_EXEC): $(GREEDY_OBJ) $(OPTIMIZED_OBJ) $(TEST_OBJ)
	$(CC) $(CFLAGS) -o $@ $^

# Compile source files
$(BUILD_DIR)/%.o: $(SRC_DIR)/%.c
	$(CC) $(CFLAGS) -c $< -o $@

# Compile test files
$(BUILD_DIR)/%.o: $(TEST_DIR)/%.c
	$(CC) $(CFLAGS) -c $< -o $@

# Run tests
test: $(TEST_EXEC)
	@echo "Running tests..."
	@$(TEST_EXEC)

# Debug build
debug: CFLAGS += $(DEBUG_FLAGS)
debug: clean all

# Clean build artifacts
clean:
	rm -rf $(BUILD_DIR) $(BIN_DIR)
	rm -f *.o test_solution

# Show help
help:
	@echo "Available targets:"
	@echo "  all    - Build test executable (default)"
	@echo "  test   - Build and run tests"
	@echo "  debug  - Build with debug symbols"
	@echo "  clean  - Remove build artifacts"
	@echo "  dirs   - Create build directories"
	@echo "  help   - Show this help message"
	@echo ""
	@echo "Project Structure:"
	@echo "  src/     - Source files (.c)"
	@echo "  include/ - Header files (.h)"
	@echo "  test/    - Test files"
	@echo "  build/   - Compiled object files (.o)"
	@echo "  bin/     - Executable binaries"
