<template>
  <div class="sidebar">
    <div class="container sidebar-sticky">
      <div class="sidebar-about">
        <h1>
          <g-link to="/">{{ $static.metadata.siteName }}</g-link>
        </h1>
        <p class="lead">{{ $static.metadata.siteDescription }}</p>
      </div>

      <nav class="sidebar-nav">
        <g-link
          class="sidebar-nav-item"
          v-for="item in menu"
          :key="item.href"
          :to="item.href"
        >
          {{ item.title }}
        </g-link>
        <a class="sidebar-nav-item" :href="$static.metadata.githubRepo">
          View on GitHub
        </a>
      </nav>

      <p>&copy; {{ new Date().getFullYear() }}. All rights reserved.</p>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
  data: function () {
    return {
      menu: [
        { title: 'Getting Started', href: '/' },
        { title: 'User Guide', href: '/docs', submenu: [] }
      ]
    }
  }
})
</script>

<static-query>
query {
  metadata {
    siteName
    siteDescription
    githubRepo
  }
}
</static-query>
