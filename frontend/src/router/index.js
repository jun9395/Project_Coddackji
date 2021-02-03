import Vue from 'vue';
import VueRouter from 'vue-router';

import Home from '@/views/Home.vue';
import LogIn from '@/views/Accounts/LogIn.vue';
import SignUp from '@/views/Accounts/SignUp.vue';
import FindPassword from '@/views/Accounts/FindPassword.vue';
import UserProfile from '@/views/Accounts/UserProfile.vue'

import AllProblemRank from '@/views/Problems/AllProblemRank.vue'
import ProblemRankList from '@/views/Problems/ProblemRankList.vue'
import ProblemDetail from '@/views/Problems/ProblemDetail.vue'
import SolveProblemPage from '@/views/Problems/SolveProblemPage'
import SolveResult from '@/views/Problems/SolveResult'

import Notice from '@/views/Notices/Notice.vue'
import CoFAQ from '@/views/Notices/CoFAQ.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/accounts/login',
    name: 'LogIn',
    component: LogIn,
  },
  {
    path: '/accounts/signup',
    name: 'SignUp',
    component: SignUp,
  },
  {
    path: '/accounts/findpassword',
    name: 'FindPassword',
    component: FindPassword,
  },
  {
    path: '/problem/rank',
    name: 'AllProblemRank',
    component: AllProblemRank,
  },
  {
    path: '/problem/rank/:problemrank',
    name: 'ProblemRankList',
    component: ProblemRankList,
  },
  {
    path: '/problem/problem_:problemnumber/detail',
    name: 'ProblemDetail',
    component: ProblemDetail,
  },
  {
    // path: '/user/:name', 단, post로 보내지는 데이터는 이메일
    // path: '/user/:email', get 요청 이 경우 login 성공 후 localstorage에 email 저장 (백에서 보내줄 필요 X)
    path: '/user',
    name: 'UserProfile',
    component: UserProfile,
  },
  {
    path: '/problem/problem_:problemnumber/solve',
    name: 'SolveProblemPage',
    component: SolveProblemPage,
  },
  {
    path: '/problem/problem_:problemnumber/result',
    name: 'SolveResult',
    component: SolveResult,
  },
  {
    path: '/codackji/notices',
    name: 'Notice',
    component: Notice,
  },
  {
    path: '/codackji/CoFAQ',
    name: 'CoFAQ',
    component: CoFAQ,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;