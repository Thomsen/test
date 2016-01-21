<template>
  <div id="app">
    <img class="logo" src="./assets/logo.png">
    <hello></hello>
    <Learn></Learn>
  </div>
</template>

<script>
import Hello from './components/Hello'
import Learn from './components/Learn'

export default {
  components: {
    Hello,
    Learn
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
