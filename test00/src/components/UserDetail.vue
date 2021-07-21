<template>
  <el-button type="text" @click="dialogVisible = true">{{ user }} </el-button>
  <div>
    <el-dialog :title="user.name" v-model="dialogVisible" width="50%">
      <div>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="Consistency" name="1">
            <div>
              Consistent with real life: in line with the process and logic of
              real life, and comply with languages and habits that the users are
              used to;
            </div>
            <div>
              Consistent within interface: all elements should be consistent,
              such as: design style, icons and texts, position of elements, etc.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Feedback" name="2">
            <div>
              Operation feedback: enable the users to clearly perceive their
              operations by style updates and interactive effects;
            </div>
            <div>
              Visual feedback: reflect current state by updating or rearranging
              elements of the page.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Efficiency" name="3">
            <div>
              Simplify the process: keep operating process simple and intuitive;
            </div>
            <div>
              Definite and clear: enunciate your intentions clearly so that the
              users can quickly understand and make decisions;
            </div>
            <div>
              Easy to identify: the interface should be straightforward, which
              helps the users to identify and frees them from memorizing and
              recalling.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Controllability" name="4">
            <div>
              Decision making: giving advices about operations is acceptable,
              but do not make decisions for the users;
            </div>
            <div>
              Controlled consequences: users should be granted the freedom to
              operate, including canceling, aborting or terminating current
              operation.
            </div>
          </el-collapse-item>
          <el-collapse-item title="reservation" name="5">
            <el-row>
              <el-col :span="12"
                ><div class="grid-content bg-purple">
                  <span class="demonstration"></span>
                  <el-date-picker
                    v-model="value1"
                    type="date"
                    placeholder="Pick a date"
                    :default-value="new Date(2021, 7, 20)"
                  >
                  </el-date-picker></div
              ></el-col>
              <el-col :span="12"
                ><div class="grid-content bg-purple-light">
                  <el-time-select
                    v-model="value"
                    start="08:30"
                    step="00:15"
                    end="18:30"
                    placeholder="Select time"
                  >
                  </el-time-select></div
              ></el-col>
            </el-row>
          </el-collapse-item>
        </el-collapse>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "userdetail",
  setup() {},
  created() {
    console.log(this.user);
  },
  data() {
    return {
      dialogVisible: false,
      activeNames: ["1"],
      activeName: "1",
      value1: "",
      value: "",
      testdata: "test",
    };
  },
  props: {
    user: Object,
  },
  methods: {
    handleClose(done) {
      this.$confirm("Are you sure to close this dialog?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
    successmessage() {
      this.$message({
        message: "Congrats, this is a success message.",
        type: "success",
      });
    },
  },
};
</script>

<style></style>
