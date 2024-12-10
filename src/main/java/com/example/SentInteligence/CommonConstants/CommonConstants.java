package com.example.SentInteligence.CommonConstants;

public class CommonConstants {
    public static final String INSIGHT_URL = "INSIGHT_URL";
    public static final String INSIGHT_PORT = "INSIGHT_PORT";
    public static final String INSIGHT_USE_TLS = "INSIGHT_USE_TLS";
    public static final String SAMPLE_RATE_HERTZ = "SAMPLE_RATE_HERTZ";
    public static final String LANGUAGE_CODE = "LANGUAGE_CODE";
    public static final String AUDIO_FILE_PATH = "AUDIO_FILE_PATH";
    public static final String AUDIO_ENCODING_TYPE = "AUDIO_ENCODING_TYPE";
    public static final String UNKNOWN = "Unknown";

    public static final String REALTIME_AGENT_ASSIST_FLAG = "REALTIME_AGENT_ASSIST_FLAG";
    public static final String REALTIME_TRANSCRIPT_FLAG = "REALTIME_TRANSCRIPT_FLAG";
    public static final String HISTORICAL_TRANSCRIPT_FLAG = "HISTORICAL_TRANSCRIPT_FLAG";
    public static final String HISTORICAL_AGENT_ASSIST_FLAG = "HISTORICAL_AGENT_ASSIST_FLAG";

    // Serving API Properties
    public static final String SERVING_URL = "SERVING_URL";
    public static final String SERVING_PORT = "SERVING_PORT";
    public static final String SERVING_USE_TLS = "SERVING_USE_TLS";

    public static final String ORG_ID = "ORG_ID";
    public static final String CCAI_CONFIG_ID_CISCO = "CCAI_CONFIG_ID_CISCO";
    public static final String CISCO = "CISCO";
    public static final String GOOGLE = "GOOGLE";

    // env
    public static final String TARGET_ENV = "TARGET_ENV";
    public static final String DEVUS1 = "devus1";
    public static final String QAUS1 = "qaus1";
    public static final String LOADUS1 = "loadus1";
    public static final String INTGUS1 = "intgus1";
    public static final String APPSTAGING = "appstaging";
    public static final String PRODUS1 = "produs1";
    public static final String PRODSG1 = "prodsg1";

    // test constant for appstaging, load, prod
    public static final String COMPLETED_MESSAGE = "COMPLETED_MESSAGE";
    public static final String HAS_INLINE_RESPONSE = "HAS_INLINE_RESPONSE";
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";



    // application properties constants
    public static final String LLM_URL = "sentimentAnalysis.url";
//    public static final String TRANSCRIPT_JSON = "transcriptJson"; //TODO: Fix this to get from application.prop
public static final String TRANSCRIPT_JSON = "{\"took\":32,\"timed_out\":false,\"_shards\":{\"total\":2,\"successful\":2,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":{\"value\":24,\"relation\":\"eq\"},\"max_score\":null,\"hits\":[{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"9K6sqZMBqDaPhs2NUNc8\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"b8e8b744-03f6-4664-99b9-94267bdf7eaa\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718462214,\"endTimestamp\":1733718462214,\"publishTimestamp\":1733718462214,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Hello welcome to cumulus bank. how can i assist you today\\\"]\\n}\"},\"sort\":[1733718462214]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"_a6sqZMBqDaPhs2Nbdey\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"0c1bf949-aa10-4c8d-a12f-81e227f3ccfb\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718469929,\"endTimestamp\":1733718469929,\"publishTimestamp\":1733718469929,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"I need to reset my PIN\\\",\\n    \\\"confidence\\\": 0.968\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718469929]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"_q6sqZMBqDaPhs2Nb9ex\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"aff9d2fd-b2bb-4d1a-b59c-8f2bece3927f\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718470237,\"endTimestamp\":1733718470237,\"publishTimestamp\":1733718470237,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Sure. Please provide your 6 digit account number.\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Reset Pin Number\\\",\\n    \\\"displayName\\\": \\\"Reset Pin Number\\\",\\n    \\\"matchConfidence\\\": 0.98\\n  },\\n  \\\"inputText\\\": \\\"I need to reset my PIN\\\"\\n}\"},\"sort\":[1733718470237]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"B66sqZMBqDaPhs2Nj9gw\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"c88ecd5c-66b3-4131-80cd-48a7268cb922\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718478558,\"endTimestamp\":1733718478558,\"publishTimestamp\":1733718478558,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"123456\\\",\\n    \\\"confidence\\\": 0.982\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718478558]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"CK6sqZMBqDaPhs2Nkdgv\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"96352b3d-06ee-4e65-b678-4ac7171dd646\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718478851,\"endTimestamp\":1733718478851,\"publishTimestamp\":1733718478851,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"<speak> Your account number is <say-as interpret-as=\\\\\\\"digits\\\\\\\">123456</say-as>. Please confirm. </speak>\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Reset Pin Number\\\",\\n    \\\"displayName\\\": \\\"Reset Pin Number\\\",\\n    \\\"matchConfidence\\\": 1.0\\n  },\\n  \\\"inputText\\\": \\\"123456\\\"\\n}\"},\"sort\":[1733718478851]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Da6sqZMBqDaPhs2NsNiJ\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"8912b1b8-2357-4a12-9842-61df1868b1b0\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718487041,\"endTimestamp\":1733718487041,\"publishTimestamp\":1733718487041,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"Yes that is correct\\\",\\n    \\\"confidence\\\": 0.913\\n  }, {\\n    \\\"transcript\\\": \\\"Yes it is correct\\\",\\n    \\\"confidence\\\": 0.902\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718487041]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Dq6sqZMBqDaPhs2NstiG\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"ea6757ae-72a7-4281-a7b5-b77c3d93f8b4\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718487337,\"endTimestamp\":1733718487337,\"publishTimestamp\":1733718487337,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Thank you for that information. Please provide the phone number registered with your account.\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Capture Account Details\\\",\\n    \\\"displayName\\\": \\\"Capture Account Details\\\",\\n    \\\"matchConfidence\\\": 0.94\\n  },\\n  \\\"inputText\\\": \\\"Yes that is correct\\\"\\n}\"},\"sort\":[1733718487337]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Ia6sqZMBqDaPhs2N3djQ\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"062c5ccb-2037-4166-ae4c-8fd37608403a\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718498391,\"endTimestamp\":1733718498391,\"publishTimestamp\":1733718498391,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"9363109481\\\",\\n    \\\"confidence\\\": 0.978\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718498391]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Iq6sqZMBqDaPhs2N39jO\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"c88fc356-cc18-44db-8df9-9b0c7f80953d\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718498967,\"endTimestamp\":1733718498967,\"publishTimestamp\":1733718498967,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"<speak> Thanks. Your phone number matches with our records. We have sent you a temporary pin  Kindly enter the same. </speak>\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Capture Account Details\\\",\\n    \\\"displayName\\\": \\\"Capture Account Details\\\",\\n    \\\"matchConfidence\\\": 1.0\\n  },\\n  \\\"inputText\\\": \\\"9363109481\\\"\\n}\"},\"sort\":[1733718498967]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Kq6tqZMBqDaPhs2NDNjq\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"fe43dd26-677b-49ab-bcd7-97c5c59a7e46\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718510498,\"endTimestamp\":1733718510498,\"publishTimestamp\":1733718510498,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"1234\\\",\\n    \\\"confidence\\\": 0.981\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718510498]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"K66tqZMBqDaPhs2NDtjn\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"fac21b1b-f1b2-405f-9f5f-1d01c7ea350a\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718511049,\"endTimestamp\":1733718511049,\"publishTimestamp\":1733718511049,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Thank you. Please enter the new PIN that you would like to use.\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Capture Account Details\\\",\\n    \\\"displayName\\\": \\\"Capture Account Details\\\",\\n    \\\"matchConfidence\\\": 1.0\\n  },\\n  \\\"inputText\\\": \\\"1234\\\"\\n}\"},\"sort\":[1733718511049]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Ma6tqZMBqDaPhs2NKthn\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"338c0253-7d59-4b2c-bd79-cfd73f6636d3\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718518155,\"endTimestamp\":1733718518155,\"publishTimestamp\":1733718518155,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"4321\\\",\\n    \\\"confidence\\\": 0.975\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718518155]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Mq6tqZMBqDaPhs2NLNhk\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"6375c523-0104-469e-8fa9-2907f51bbf45\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718518792,\"endTimestamp\":1733718518792,\"publishTimestamp\":1733718518792,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Your PIN has been successfully reset. Please use the new PIN to access your account. Is there anything else I can assist you with?\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Capture Account Details\\\",\\n    \\\"displayName\\\": \\\"Capture Account Details\\\",\\n    \\\"matchConfidence\\\": 1.0\\n  },\\n  \\\"inputText\\\": \\\"4321\\\"\\n}\"},\"sort\":[1733718518792]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Pq6tqZMBqDaPhs2NX9hk\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"f29e81f8-0192-4134-b1e7-17ebe526d74b\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718531800,\"endTimestamp\":1733718531800,\"publishTimestamp\":1733718531801,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"I want to transfer funds to another account\\\",\\n    \\\"confidence\\\": 0.973\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718531801]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"P66tqZMBqDaPhs2NY9hY\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"61972f26-7856-4d45-a921-745cde04dffe\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718532485,\"endTimestamp\":1733718532485,\"publishTimestamp\":1733718532485,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": \\\"Id be happy to help with that. Could you tell me more about the issue youre experiencing?\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Transfer money\\\",\\n    \\\"displayName\\\": \\\"Transfer money\\\",\\n    \\\"matchConfidence\\\": 0.96\\n  },\\n  \\\"inputText\\\": \\\"I want to transfer funds to another account\\\"\\n}\"},\"sort\":[1733718532485]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Sq6tqZMBqDaPhs2Nhtit\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"51473dc2-2b43-481f-8e8a-0cf37212b980\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718541658,\"endTimestamp\":1733718541658,\"publishTimestamp\":1733718541658,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"Just transaction not processed\\\",\\n    \\\"confidence\\\": 0.933\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718541658]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"S66tqZMBqDaPhs2NiNiq\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"d729280a-56f1-4071-8ab7-0a49b54a2804\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718542271,\"endTimestamp\":1733718542271,\"publishTimestamp\":1733718542271,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Sorry,  I hear that wasnt able to help you. Please try asking a different question.\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Not helpful\\\",\\n    \\\"displayName\\\": \\\"Not helpful\\\",\\n    \\\"matchConfidence\\\": 0.63\\n  },\\n  \\\"inputText\\\": \\\"Just transaction not processed\\\"\\n}\"},\"sort\":[1733718542271]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"Wq6tqZMBqDaPhs2Nt9jE\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"30f2aad3-11c3-4da4-b405-498a82a8aaaf\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718554484,\"endTimestamp\":1733718554484,\"publishTimestamp\":1733718554484,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"It says transaction not processed\\\",\\n    \\\"confidence\\\": 0.877\\n  }, {\\n    \\\"transcript\\\": \\\"It's this transaction not processed\\\",\\n    \\\"confidence\\\": 0.816\\n  }, {\\n    \\\"transcript\\\": \\\"It says the transaction not processed\\\",\\n    \\\"confidence\\\": 0.813\\n  }, {\\n    \\\"transcript\\\": \\\"It's is the transaction not processed\\\",\\n    \\\"confidence\\\": 0.795\\n  }, {\\n    \\\"transcript\\\": \\\"It's is a transaction not processed\\\",\\n    \\\"confidence\\\": 0.773\\n  }, {\\n    \\\"transcript\\\": \\\"Exist transaction not processed\\\",\\n    \\\"confidence\\\": 0.753\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718554484]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"W66tqZMBqDaPhs2NudjC\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"7e6f7bf9-8101-4508-a95e-6c8d9b291af9\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718554802,\"endTimestamp\":1733718554802,\"publishTimestamp\":1733718554802,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Sorry,  I hear that wasn't able to help you. Please try asking a different question.\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Not helpful\\\",\\n    \\\"displayName\\\": \\\"Not helpful\\\",\\n    \\\"matchConfidence\\\": 0.58\\n  },\\n  \\\"inputText\\\": \\\"It says transaction not processed\\\"\\n}\"},\"sort\":[1733718554802]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"XK6tqZMBqDaPhs2N2tj_\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"d3692644-1525-4981-9202-c0226d5c3e7d\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718563103,\"endTimestamp\":1733718563103,\"publishTimestamp\":1733718563103,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Sorry, I am unable to understand your message. Could you please rephrase it?\\\"]\\n}\"},\"sort\":[1733718563103]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"fa6uqZMBqDaPhs2NDNhU\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"866c2dbb-6a41-4225-9d8e-4915f3c48d52\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718576088,\"endTimestamp\":1733718576088,\"publishTimestamp\":1733718576088,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"Every time I try to complete the transfer I get an error message saying transaction cannot be processed\\\",\\n    \\\"confidence\\\": 0.944\\n  }, {\\n    \\\"transcript\\\": \\\"Every time I tried to complete the transfer I get an error message saying transaction cannot be processed\\\",\\n    \\\"confidence\\\": 0.934\\n  }, {\\n    \\\"transcript\\\": \\\"Every time I try to complete the transfer I get another message saying transaction cannot be processed\\\",\\n    \\\"confidence\\\": 0.931\\n  }, {\\n    \\\"transcript\\\": \\\"Every time I try to complete a transfer I get an error message saying transaction cannot be processed\\\",\\n    \\\"confidence\\\": 0.929\\n  }, {\\n    \\\"transcript\\\": \\\"Every time I tried to complete the transfer I get another message saying transaction cannot be processed\\\",\\n    \\\"confidence\\\": 0.922\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718576088]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"fq6uqZMBqDaPhs2NDthQ\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"2e83a7eb-1ccb-4a6b-8422-f7bb7690611e\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718576697,\"endTimestamp\":1733718576697,\"publishTimestamp\":1733718576697,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"I’m sorry for the inconvenience. Let’s check a few things. Have you ensured that all the recipient details and the amount are correct?\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Transacation cannot be processed\\\",\\n    \\\"displayName\\\": \\\"Transacation cannot be processed\\\",\\n    \\\"matchConfidence\\\": 0.99\\n  },\\n  \\\"inputText\\\": \\\"Every time I try to complete the transfer I get an error message saying transaction cannot be processed\\\"\\n}\"},\"sort\":[1733718576697]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"m66uqZMBqDaPhs2NT9hE\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"f083d95e-bb4f-4224-b9a6-5195e1b42d8c\",\"messageId\":\"\",\"role\":\"CALLER\",\"insightType\":\"transcript\",\"serviceProvider\":\"cisco\",\"startTimestamp\":1733718593234,\"endTimestamp\":1733718593234,\"publishTimestamp\":1733718593234,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"alternatives\\\": [{\\n    \\\"transcript\\\": \\\"Yes everything is correct I have trouble ticket is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.83\\n  }, {\\n    \\\"transcript\\\": \\\"Yes everything is correct I have trouble ticket this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.823\\n  }, {\\n    \\\"transcript\\\": \\\"Yes everything is correct I had trouble ticket this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.82\\n  }, {\\n    \\\"transcript\\\": \\\"Its everything is correct I have trouble ticket this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.812\\n  }, {\\n    \\\"transcript\\\": \\\"Its everything is correct I had trouble ticket this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.81\\n  }, {\\n    \\\"transcript\\\": \\\"Yes everything is correct I have trouble ticket but it's really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.796\\n  }, {\\n    \\\"transcript\\\": \\\"Yes everything is correct I rejected this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.779\\n  }, {\\n    \\\"transcript\\\": \\\"Its everything is correct I rejected this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.769\\n  }, {\\n    \\\"transcript\\\": \\\"Yes everything is correct rejected this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.766\\n  }, {\\n    \\\"transcript\\\": \\\"Yes everything is correct triple checked it this is really frustrating because I need to get this done urgently\\\",\\n    \\\"confidence\\\": 0.755\\n  }],\\n  \\\"isFinal\\\": true\\n}\"},\"sort\":[1733718593234]},{\"_index\":\"wccai-insights-index-000025\",\"_id\":\"nK6uqZMBqDaPhs2NUdhF\",\"_score\":null,\"_source\":{\"orgId\":\"fa6bed78-0595-4cca-84bc-014f4680fed8\",\"conversationId\":\"dfeda5ca-83c6-4499-a704-948fbbe14096\",\"roleId\":\"supervisor\",\"utteranceId\":\"2ace6d4c-8cd8-4302-a14c-be722421b4b2\",\"messageId\":\"\",\"role\":\"IVR\",\"insightType\":\"virtual_agent\",\"serviceProvider\":\"native_basic_virtual_agent\",\"startTimestamp\":1733718593817,\"endTimestamp\":1733718593817,\"publishTimestamp\":1733718593817,\"isFinal\":true,\"configId\":\"NATIVE_BASIC_VIRTUAL_AGENT\",\"languageCode\":\"en-US\",\"content\":\"{\\n  \\\"replyText\\\": [\\\"Please wait while I redirect you to one of our team member.\\\"],\\n  \\\"intent\\\": {\\n    \\\"name\\\": \\\"Get this done urgently\\\",\\n    \\\"displayName\\\": \\\"Get this done urgently\\\",\\n    \\\"matchConfidence\\\": 0.97\\n  },\\n  \\\"inputText\\\": \\\"Yes everything is correct I have trouble ticket is really frustrating because I need to get this done urgently\\\",\\n  \\\"exitEvent\\\": {\\n    \\\"eventType\\\": \\\"AGENT_TRANSFER\\\",\\n    \\\"metadata\\\": {\\n      \\\"previous-intent\\\": {\\n        \\\"name\\\": \\\"Transacation cannot be processed\\\",\\n        \\\"entities\\\": []\\n      },\\n      \\\"current-intent\\\": {\\n        \\\"name\\\": \\\"Get this done urgently\\\",\\n        \\\"entities\\\": []\\n      }\\n    }\\n  }\\n}\"},\"sort\":[1733718593817]}]}}";
}
