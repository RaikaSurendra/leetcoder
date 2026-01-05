# Rust Solution

## Structure
```
rust/
├── Cargo.toml
├── src/
│   └── lib.rs
├── target/          (created after build)
└── README.md
```

## Run Tests

```bash
cd rust
cargo test
```

## Run Tests with Output

```bash
cargo test -- --nocapture
```

## Build

```bash
cargo build
cargo build --release  # optimized build
```

## Check Code

```bash
cargo check
cargo clippy  # linting
```

## Approaches

### Greedy (Sorting)
- Sort capacities in descending order using `sort_unstable_by`
- Pick boxes from largest to smallest
- Time: O(n + m log m)
- Space: O(1)

### Optimized (Frequency Counting)
- Count boxes by capacity (max 50)
- Pick from capacity 50 down to 1
- Time: O(n + m + 50)
- Space: O(50)

## Notes
- Tests are integrated in `src/lib.rs` using `#[cfg(test)]`
- Build artifacts go to `target/` directory
- Use `cargo clean` to remove build artifacts
