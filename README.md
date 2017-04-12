[![Codacy Badge](https://api.codacy.com/project/badge/Grade/110c1e99ef854713bbef5dc4209be198)](https://www.codacy.com/app/crizin/dokebirizer?utm_source=github.com&utm_medium=referral&utm_content=crizin/dokebirizer&utm_campaign=badger)
[![Build Status](https://travis-ci.org/crizin/dokebirizer.svg?branch=master)](https://travis-ci.org/crizin/dokebirizer)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/110c1e99ef854713bbef5dc4209be198)](https://www.codacy.com/app/crizin/dokebirizer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=crizin/dokebirizer&amp;utm_campaign=Badge_Grade)

# 개요

한국어 문장을 [도깨비말](https://namu.wiki/w/%EB%8F%84%EA%B9%A8%EB%B9%84%EB%A7%90)로 변환해주거나 반대로 변환해주는 라이브러리.

# Dokebirizer 생성자 파라미터

## Dokebirizer.Policy

두 번째 음절의 모음을 어떻게 표현할지 결정하는 방법. 기본값은 `Policy.Split`.

### Policy.Split
이중모음을 분리시켜 사용하는 방법.

### Policy.Copy
이중모음을 그대로 두번째 중성에도 가져가 사용하는 방법.

### Policy.Transform
이중모음을 첫글자 중성에는 그대로, 두번째 글자의 중성에는 이중모음의 두 번째 모음으로 중성을 사용하는 방법.

# Jaso.Chosung

두 번째 음절의 초성으로 사용할 자음을 지정. 기본값은 'ㅂ'.