<template>
  <div>
    <v-sheet height="300" align="center">
      <v-row class="fill-height" align="center" justify="center">
        <div class="display-3">
          <v-img src="@/assets/img/banner/problem_banner.png" height="300">
            <v-container fill-height fluid>
              <v-row class="fill-height" align="center" justify="center">
                <v-col>
                  <div class="banner text-center" style="font-size: 40px">
                    해설과 제출 결과
                  </div>
                  <p class="guide text-center" style="font-size:15px; line-height: 25px;">
                    제출한 코드의 결과를 확인하고 제공되는 예시코드와 비교할 수 있어요<br />
                    문제 풀이에 대한 자세한 설명은 해설보기를 참고 해주세요
                  </p>
                </v-col>
              </v-row>
            </v-container>
          </v-img>
        </div>
      </v-row>
    </v-sheet>
    <v-chip
      @click="$router.go(-1)"
      outlined
      color="indigo darken-1"
      style="position: absolute; top: 0; left: 10px; z-index: 50;"
    >
      <v-icon left>
        mdi-arrow-left-bold
      </v-icon>
      이전 페이지로
    </v-chip>

    <v-tabs fixed-tabs v-model="tabs" background-color="grey lighten-1" dark>
      <v-tab>채점 결과</v-tab>
      <v-tab>예시코드와 비교</v-tab>
      <v-tab>해설 보기</v-tab>

      <v-tabs-items v-model="tabs">
        <v-tab-item>
          <v-container class="mt-5">
            <v-row align="center">
              <v-col>
                <div class="mb-3" style="font-size:20px;">
                  제출한 코드의 정답 여부와 어떤 오류가 있는지 코기와 함께 확인해보세요!
                </div>
                <v-divider></v-divider>
              </v-col>
            </v-row>
            <v-row>
              <v-col align="right" class="mb-3" cols="6">
                <img src="@/assets/img/cogi.png" width="90px" height="auto" />
              </v-col>
              <v-col align="left" cols="6">
                <div v-show="hint" class="balloon_03">
                  <p v-if="isCorrect" class="typing-txt">너무 잘했어! 정답이야!</p>
                  <p v-if="!isCorrect" class="pa-3" v-html="handleNewLine(error)"></p>
                </div>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="mb-3" cols="11" align="right">
                <v-chip @click="hint = !hint" color="indigo darken-1" outlined>힌트 보기</v-chip>
              </v-col>
            </v-row>
            <v-row no-gutters justify="center">
              <v-col cols="12" md="4">
                <div v-if="yourAnswer">
                  <v-sheet color="amber lighten-2" dark>
                    <v-card-text align="center" class="pa-1" style="font-size:18px;"
                      >입력한 코드 출력</v-card-text
                    >
                  </v-sheet>
                  <v-sheet class="guide">
                    <v-textarea
                      class="myselfcounter"
                      solo
                      no-resize
                      full-width
                      rows="5"
                      :value="yourAnswer"
                      readonly
                    ></v-textarea>
                  </v-sheet>
                </div>
                <v-card loading="red darken-1" v-else>
                  <v-sheet color="amber lighten-2" dark>
                    <v-card-text align="center" class="pa-1" style="font-size:18px;"
                      >입력한 코드 출력</v-card-text
                    >
                  </v-sheet>
                  <v-sheet class="guide">
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                  </v-sheet>
                </v-card>
              </v-col>

              <v-col cols="0" md="1"></v-col>

              <v-col cols="12" md="4">
                <div v-if="correctAnswer">
                  <v-sheet color="indigo lighten-1" dark>
                    <v-card-text align="center" class="pa-1" style="font-size:18px;"
                      >올바른 출력</v-card-text
                    >
                  </v-sheet>
                  <v-sheet class="guide">
                    <v-textarea
                      solo
                      no-resize
                      rows="5"
                      :value="correctAnswer"
                      readonly
                    ></v-textarea>
                  </v-sheet>
                </div>

                <v-card loading="red darken-1" v-else>
                  <v-sheet color="indigo lighten-1" dark>
                    <v-card-text align="center" class="pa-1" style="font-size:18px;"
                      >올바른 출력</v-card-text
                    >
                  </v-sheet>
                  <v-sheet class="guide">
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                    <v-card-text></v-card-text>
                  </v-sheet>
                </v-card>
              </v-col>

              <v-col cols="0" md="5"></v-col>
            </v-row>
          </v-container>
        </v-tab-item>

        <v-tab-item>
          <v-container class="mt-5">
            <v-row>
              <v-col>
                <div class="mb-3" style="font-size:20px;">
                  예시코드는 예시일 뿐 절대적인 정답이 아니니 참고하세요!
                </div>
                <v-divider></v-divider>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="11" align="right">
                <v-chip
                  v-if="!isAnswer"
                  outlined
                  color="light-green darken-1"
                  @click="isAnswer = !isAnswer"
                  >예시코드보기<v-icon right>mdi-script-text</v-icon></v-chip
                >
                <v-chip v-else outlined color="red darken-1" @click="isAnswer = !isAnswer"
                  >예시코드접기<v-icon right>mdi-script</v-icon></v-chip
                >
              </v-col>
            </v-row>
            <v-row class="mt-5" no-gutters justify="center">
              <v-col cols="12" sm="8" md="4">
                <div>
                  <v-sheet color="amber lighten-2" dark>
                    <v-card-text align="center" class="pa-1" style="font-size:18px;"
                      >제출한 코드</v-card-text
                    >
                  </v-sheet>
                  <v-sheet class="guide">
                    <v-textarea solo no-resize rows="20" :value="yourCode" readonly></v-textarea>
                  </v-sheet>
                </div>
              </v-col>
              <v-col cols="0" sm="1"></v-col>

              <v-col cols="12" sm="8" md="4">
                <v-card>
                  <v-sheet color="indigo lighten-1" dark>
                    <v-card-text align="center" class="pa-1" style="font-size:18px;"
                      >예시코드</v-card-text
                    >
                  </v-sheet>
                  <v-sheet v-show="!isAnswer">
                    <v-card-text align="left" class="pd-3 guide"
                      >예시코드를 보려면 예시코드보기를 눌러주세요</v-card-text
                    >
                  </v-sheet>
                </v-card>
                <v-sheet class="guide" v-show="isAnswer">
                  <v-textarea solo no-resize rows="20" :value="correctCode" readonly></v-textarea>
                </v-sheet>
              </v-col>
            </v-row>
          </v-container>
        </v-tab-item>

        <v-tab-item>
          <v-container class="mt-5">
            <v-row align="center">
              <v-col>
                <div class="mb-3" style="font-size:20px;">
                  해설을 보며 어려웠던 부분을 다시 학습해보세요!
                </div>
                <v-divider></v-divider>
              </v-col>
            </v-row>
            <v-row>
              <v-carousel height="750" v-model="cmodel">
                <v-carousel-item v-for="i in imageNumber" :key="i">
                  <v-sheet color="white" height="100%" tile>
                    <v-row class="fill-height" align="center" justify="center">
                      <div class="display-3" v-if="imageContent">
                        <v-img width="700" height="auto" :src="imageContent[i - 1]" />
                      </div>
                    </v-row>
                  </v-sheet>
                </v-carousel-item>
              </v-carousel>
            </v-row>
          </v-container>
        </v-tab-item>
      </v-tabs-items>
    </v-tabs>
  </div>
</template>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'SolveResult',
  data: () => {
    return {
      tabs: null,
      description: false,
      isCorrect: false,
      error: '',
      isAnswer: false,
      yourAnswer: '',
      correctAnswer: '',
      yourCode: '',
      correctCode: '',
      imageNumber: 0,
      imageContent: '',
      cmodel: 0,
      hint: false,
      colors: ['primary', 'secondary', 'yellow darken-2', 'red', 'orange'],
    };
  },
  created() {
    const resultInfo = {
      token: localStorage.getItem('jwt'),
      solved_problem_number: this.$route.params.resultnumber,
    };

    axios
      .post(`${SERVER_URL}/codeAPI/result`, resultInfo)
      .then((response) => {
        this.isCorrect = response.data.answer;
        if (response.data.error === 'simpleerror') {
          this.error = '아쉽게도 정답이 아니네!\n다시 생각해 볼까?';
        } else {
          this.error = response.data.error;
        }

        if (!this.error) this.error = '다시 한 번 풀어보자!';

        this.yourAnswer = response.data.my_output;
        if (!this.yourAnswer) {
          this.yourAnswer = '빈 값이 출력되고 있어!';
        }
        this.correctAnswer = response.data.correct_output;
        this.yourCode = response.data.my_code;
        this.correctCode = response.data.correct_code;
        if (!this.correctCode) {
          this.correctCode =
            '방금 제출한 언어의 답은 아직 준비되지 않았어\n금방 준비할 테니 조금만 기다려줘!';
        }
        this.imageNumber = response.data.img_number;
        this.imageContent = response.data.img_content;
      })
      .catch((error) => {
        if (error.response.status === 401) {
          alert('세션이 만료되었습니다.');
          this.$emit('expireLogin');
        } else if (error.response.status === 403) {
          alert('권한이 없습니다.');
          this.$router.push({ name: 'Home' });
        } else {
          alert('서버와 통신할 수 없습니다.');
        }
      });
  },
  methods: {
    handleNewLine(str) {
      return String(str).replace(/(?:\r\n|\r|\n)/g, '</br>');
    },
  },
};
</script>

<style>
.balloon_03 {
  color: white;
  position: relative;
  margin: 50px;
  width: 450px;
  height: 120px;
  background: #333333;
  border-radius: 10px;
}
.balloon_03:after {
  color: white;
  border-top: 15px solid #333333;
  border-left: 15px solid transparent;
  border-right: 0px solid transparent;
  border-bottom: 0px solid transparent;
  content: '';
  position: absolute;
  top: 10px;
  left: -15px;
}
p.typing-txt {
  padding: 3px;
  position: relative;
  display: inline-block;
  height: 22px;
  overflow: hidden;
  animation: typingAni 3s steps(20, end) 1;
}
p.typing-txt::after {
  padding: 3px;
  position: absolute;
  display: block;
  content: '';
  width: 50px;
  height: 22px;
  top: 3px;
  right: 0;
  border-right: 1px solid rgb(255, 255, 255);
  animation: cursor 0.7s step-end infinite;
}
@keyframes typingAni {
  0% {
    width: 0%;
  }
  100% {
    width: 150px;
  }
}
@keyframes cursor {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}

.v-text-field__details {
  display: none !important;
}
</style>
