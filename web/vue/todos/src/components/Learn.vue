<template>
  <div id="learn">
  <p>{{ message }}</p>
  <input v-model="message"/>
  <ul>
  <li v-for="todo in todos">
  {{ todo.text }}
  </li>
  </ul>
  </div>
  <button v-on:click="reverseMessage">Reverse Message</button>
</template>

<script>
  export default {
    data () {
      return {
        message: 'two way binding',
        todos: [
        { text: 'Learn javascript' },
        { text: 'todo one' },
        { text: 'todo two' }
        ]
      }
    },
    methods: {
      reverseMessage: function () {
        this.message = this.message.split('').reverse().join('')
      }
    }
  }
</script>
