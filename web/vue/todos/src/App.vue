<template>
  <div id="app">
    <img class="logo" src="./assets/logo.png">
    <hello></hello>
    <Learn></Learn>
    <todo></todo>
  </div>
</template>

<script>
import Hello from './components/Hello'
import Learn from './components/Learn'
import Todo from './components/Todo'

export default {
  components: {
    Hello,
    Learn,
    Todo
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
