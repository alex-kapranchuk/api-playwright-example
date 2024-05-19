# Project Overview

This project utilizes a set of classes and patterns to ensure modular, maintainable, and testable code. Below is an overview of the main components and their roles.

## Components

### PlaywrightSingleton
Provides a single Playwright instance for all tests.

### PostFactory
Used to create data for queries.

### ApiCommand
An interface for implementing the Command pattern, which makes the code more modular and understandable.

### ApiClient
Initialized using Singleton for Playwright.

### ApiAssertions
A class for validating API responses.

### ApiTest
Uses a PlaywrightSingleton to initialize a Playwright and passes it to the ApiClient.
