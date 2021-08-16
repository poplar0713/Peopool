<template>
  <el-calendar>
    <template #dateCell="{data}">
      <p :class="data.isSelected ? 'is-selected' : ''">
        {{
          data.day
            .split("-")
            .slice(1)
            .join("-")
        }}
        {{ data.isSelected ? "✔️" : "" }}
      </p>
    </template>
  </el-calendar>
</template>

<script></script>

<style>
.is-selected {
  color: #1989fa;
}
</style>
