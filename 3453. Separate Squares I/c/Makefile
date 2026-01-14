# Makefile for LeetCode 3453: Separate Squares I - C Implementation
# Optimized for maximum performance

CC = gcc
CFLAGS = -O3 -march=native -ffast-math -Wall -Wextra -std=c11
CFLAGS_DEBUG = -O0 -g -Wall -Wextra -std=c11
LDFLAGS = -lm

# Targets
TARGET = solution
TARGET_DEBUG = solution_debug

# Source files
SRC = solution_binary_search.c

# Optimization flags
OPT_FLAGS = -funroll-loops -finline-functions -fomit-frame-pointer

.PHONY: all clean run benchmark debug test

all: $(TARGET)

# Optimized build
$(TARGET): $(SRC)
	$(CC) $(CFLAGS) $(OPT_FLAGS) -o $@ $^ $(LDFLAGS)
	@echo "✓ Built optimized binary: $(TARGET)"
	@echo "  Compiler: $(CC)"
	@echo "  Flags: $(CFLAGS) $(OPT_FLAGS)"

# Debug build
debug: $(TARGET_DEBUG)

$(TARGET_DEBUG): $(SRC)
	$(CC) $(CFLAGS_DEBUG) -o $@ $^ $(LDFLAGS)
	@echo "✓ Built debug binary: $(TARGET_DEBUG)"

# Run with tests and benchmarks
run: $(TARGET)
	@echo "Running tests and benchmarks..."
	@./$(TARGET)

# Run only benchmarks
benchmark: $(TARGET)
	@echo "Running benchmarks only..."
	@./$(TARGET) | grep -A 20 "Running Benchmarks"

# Run only tests
test: $(TARGET)
	@echo "Running tests only..."
	@./$(TARGET) | grep -A 10 "Running Tests"

# Clang build (alternative compiler)
clang: CC = clang
clang: $(TARGET)

# Performance profiling with perf (Linux only)
profile: $(TARGET)
	perf record -g ./$(TARGET)
	perf report

# Memory profiling with valgrind
memcheck: $(TARGET_DEBUG)
	valgrind --leak-check=full --show-leak-kinds=all ./$(TARGET_DEBUG)

# Assembly output for optimization inspection
asm: $(SRC)
	$(CC) $(CFLAGS) $(OPT_FLAGS) -S -o solution.s $^
	@echo "✓ Generated assembly: solution.s"

# Clean build artifacts
clean:
	rm -f $(TARGET) $(TARGET_DEBUG) solution.s *.o perf.data*
	@echo "✓ Cleaned build artifacts"

# Help
help:
	@echo "Available targets:"
	@echo "  make          - Build optimized binary"
	@echo "  make run      - Build and run tests + benchmarks"
	@echo "  make test     - Run tests only"
	@echo "  make benchmark- Run benchmarks only"
	@echo "  make debug    - Build debug version"
	@echo "  make clang    - Build with clang compiler"
	@echo "  make asm      - Generate assembly output"
	@echo "  make memcheck - Run valgrind memory check"
	@echo "  make clean    - Remove build artifacts"
